
### 环境
* [virtualbox](https://www.virtualbox.org/wiki/Linux_Downloads)
* [centos6.8_64](http://vault.centos.org/6.8/isos/x86_64/)
* [javase6](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html)
* [zookeeper-3.4.6](http://archive.apache.org/dist/zookeeper/zookeeper-3.4.6/)

### 安装jdk
略
#### 安装zk
* 解压
> tar zxvf zookeeper-3.4.6.tar.gz -C /opt/
* 创建事务日志目录
> mkdir -p /opt/zookeeper-3.4.6/tmp
* 修改配置
> cp /opt/zookeeper-3.4.6/zoo_sample.cfg /opt/zookeeper-3.4.6/zoo.cfg</br>
vi zoo.cfg </br>  
>> #数据目录</br>
dataDir=/opt/zookeeper-3.4.6/tmp</br>
#dataLogDir，事务日志目录，默认为dataDir</br>


### 启动
> zkServer.sh start

### 验证
* zkServer.sh status
* jps
* zkClient.sh -server localhost:2181 -timeout 1000 [-r]
