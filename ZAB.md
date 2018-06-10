# "2pc" in ZK
集群中节点收到客户端的非事务(读)请求,就直接返回;
节点收到客户端的事务请求(写，接收请求如果是follower,会将事务请求proposal转发给leader),
leader会把这个事务转发给集群中每个follower(非observer)节点,
followers节点会响应一个ack响应,表示当前节点是否能执行这个事务,
当leader发现是过半数的节点响应了ack,则leader发起一个commit给发送了ack的followers,
当写请求完成后，会将数据同步给observer，并对客户端进行响应

leader:主导事务请求处理、顺序性保证
follower:处理非事务请求，转发事务请求给leader，参与事务, 参加leader选举投票
observer：观察集群中节点的状态变化，并对状态进行同步，不参与事务请求，不参加leader选举

# ZAB协议
> 支持**崩溃恢复**的**原子广播**协议，主要用于实现数据一致性
## 原子广播
* 对每个消息生成一个zxid（64位自增）
* 带有zxid的消息生成一个propose分发给集群中的每个follower
* followers把proposal这个事务写入磁盘，返回一个ack
* leader接收合法数量的ack以后，再发起commit请求

## 崩溃恢复
当leader宕掉或集群中过半数节点与leader失联，则会进入崩溃恢复阶段
