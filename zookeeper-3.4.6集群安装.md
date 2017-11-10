
### 环境
* [virtualbox](https://www.virtualbox.org/wiki/Linux_Downloads)
* [centos6.8_64](http://vault.centos.org/6.8/isos/x86_64/)
* [javase6](http://www.oracle.com/technetwork/java/javase/downloads/java-archive-downloads-javase6-419409.html)
* [zookeeper-3.4.6](http://archive.apache.org/dist/zookeeper/zookeeper-3.4.6/)

### 安装zookeeper到zk0上
服务器数2*n+1,至少3台</br>
本次安装是三台服务器，主机名分别是zk0,zk1,zk2</br>

#### 配置服务器ssh免密登录
略
#### 配置/etc/hosts
> vi /etc/hosts
>> 192.168.1.13 zk0</br>
192.168.1.5 zk1</br>
192.168.1.6 zk2</br>
#### 安装jdk
略
#### 安装zk
* 解压
> tar zxvf zookeeper-3.4.6.tar.gz -C /opt/
* 修改配置
> cp /opt/zookeeper-3.4.6/zoo_sample.cfg /opt/zookeeper-3.4.6/zoo.cfg</br>
vi zoo.cfg </br>  
>> #数据目录</br>
dataDir=/opt/zookeeper-3.4.6/tmp</br>
#dataLogDir，事务日志目录，默认为dataDir</br>
#server.id=hostname:通讯端口:选举端口<br>
server.0=zk0:2888:3888</br>
server.1=zk1:2888:3888</br>
server.2=zk2:2888:3888</br>
  
必要时修改`initLimit`，保证在initLimit*tickTime时间内能够启动所有zk服务器</br>

### 拷贝安装到zk1和zk2上
> scp -r /opt/zookeeper-3.4.6 root@zk1:/opt</br>
scp -r /opt/zookeeper-3.4.6 root@zk2:/opt

### 分别在zk0、zk1、zk2创建myid文件
zk0上/opt/zookeeper-3.4.6/tmp/myid,内容为0，与zoo.cfg中的server.id值保持一致</br>
zk1上/opt/zookeeper-3.4.6/tmp/myid,内容为1</br>
zk2上/opt/zookeeper-3.4.6/tmp/myid,内容为2</br>

### 启动
分别启动zkServer.sh start

### 验证
* zkServer.sh status
* jps
* zkClient.sh -server zk0:2181 -timeout 1000 [-r]
