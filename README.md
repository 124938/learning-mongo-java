# learning-mongo-java
This repo is created to interact with mongo using java mongo driver

## MongoDB setup on local

### Validate docker status
```
sudo service docker status

● docker.service - Docker Application Container Engine
     Loaded: loaded (/lib/systemd/system/docker.service; enabled; vendor preset: enabled)
     Active: active (running) since Fri 2021-02-19 17:06:05 IST; 1h 29min ago
TriggeredBy: ● docker.socket
       Docs: https://docs.docker.com
   Main PID: 15741 (dockerd)
      Tasks: 30
     Memory: 511.0M
     CGroup: /system.slice/docker.service
             ├─15741 /usr/bin/dockerd -H fd:// --containerd=/run/containerd/containerd.sock
             └─28731 /usr/bin/docker-proxy -proto tcp -host-ip 0.0.0.0 -host-port 27017 -container-ip 172.17.0.2 -container-port 27017

Feb 19 17:06:04 Asus dockerd[15741]: time="2021-02-19T17:06:04.507461163+05:30" level=warning msg="Your kernel does not support cgroup blkio weight"
Feb 19 17:06:04 Asus dockerd[15741]: time="2021-02-19T17:06:04.507465576+05:30" level=warning msg="Your kernel does not support cgroup blkio weight_device"
Feb 19 17:06:04 Asus dockerd[15741]: time="2021-02-19T17:06:04.507579353+05:30" level=info msg="Loading containers: start."
Feb 19 17:06:04 Asus dockerd[15741]: time="2021-02-19T17:06:04.964371742+05:30" level=info msg="Default bridge (docker0) is assigned with an IP address 172.17.0.0/16. Daemon option --bip can be used to s>
Feb 19 17:06:05 Asus dockerd[15741]: time="2021-02-19T17:06:05.109033307+05:30" level=info msg="Loading containers: done."
Feb 19 17:06:05 Asus dockerd[15741]: time="2021-02-19T17:06:05.329462154+05:30" level=info msg="Docker daemon" commit=46229ca graphdriver(s)=overlay2 version=20.10.3
Feb 19 17:06:05 Asus dockerd[15741]: time="2021-02-19T17:06:05.329501679+05:30" level=info msg="Daemon has completed initialization"
Feb 19 17:06:05 Asus systemd[1]: Started Docker Application Container Engine.
Feb 19 17:06:05 Asus dockerd[15741]: time="2021-02-19T17:06:05.470377831+05:30" level=info msg="API listen on /run/docker.sock"
Feb 19 17:36:02 Asus dockerd[15741]: time="2021-02-19T17:36:02.417838646+05:30" level=info msg="ignoring event" container=8f17b387cb6520018d3e66594aa2c3b5489396113a5767adb90032634674bfcb module=libcontai>
```

### Check docker version
```
docker version

Client: Docker Engine - Community
 Version:           20.10.3
 API version:       1.41
 Go version:        go1.13.15
 Git commit:        48d30b5
 Built:             Fri Jan 29 14:33:21 2021
 OS/Arch:           linux/amd64
 Context:           default
 Experimental:      true

Server: Docker Engine - Community
 Engine:
  Version:          20.10.3
  API version:      1.41 (minimum version 1.12)
  Go version:       go1.13.15
  Git commit:       46229ca
  Built:            Fri Jan 29 14:31:32 2021
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.4.3
  GitCommit:        269548fa27e0089a8b8278fc4fc781d7f65a939b
 runc:
  Version:          1.0.0-rc92
  GitCommit:        ff819c7e9184c13b7c2607fe6c30ae19403a7aff
 docker-init:
  Version:          0.19.0
  GitCommit:        de40ad0
```

### Pull mongodb image
```
sudo docker pull mongo

Using default tag: latest
latest: Pulling from library/mongo
Digest: sha256:e2880855fe63908c013489e6d6f1de0052a7c93f8b36e0c48c14ae6d9dbcf379
Status: Image is up to date for mongo:latest
docker.io/library/mongo:latest
```

### Validate image
```
sudo docker images

REPOSITORY    TAG       IMAGE ID       CREATED         SIZE
mongo         latest    b8264a857eba   2 days ago      449MB
ubuntu        latest    d70eaf7277ea   3 months ago    72.9MB
hello-world   latest    bf756fb1ae65   13 months ago   13.3kB
```

### Start mongodb in docker
```
sudo mkdir -p /mongodata
sudo docker run -it -v mongodata:/data/db -p 27017:27017 --name mongodb -d mongo
sudo docker logs mongodb
```

### Create DB in mongo 
```
sudo docker exec -it mongodb bash

show dbs;
use sample_db;
db.createCollection('employee');
db.employee.insertOne({
    "firstName" : "Shreyashkumar 1613905335575",
    "lastName" : "Limbhetwala 1613905335575",
    "address" : {
        "country" : "India",
        "state" : "Maharashtra",
        "city" : "Pune 1613905335575"
    },
    "jobs" : [ 
        {
            "company" : "CTS 1613905335575",
            "start" : "11-May-2005",
            "end" : "15-Apr-2016"
        }, 
        {
            "company" : "DB 1613905335575",
            "start" : "test start",
            "end" : "test end"
        }
    ],
    "comments" : [ 
        {
            "from" : "Anyone 1613905335575",
            "message" : "Any message 1613905335575",
            "updatedAt" : NumberLong(1613905335804)
        }
    ]
})
```

### Stop/Start mongodb in docker
```
sudo docker stop mongodb
sudo docker start mongodb
sudo docker ps
```
