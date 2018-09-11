# Instruction
1. Create Docker Image
2. Deploy to K8S platform

## Create Docker Image
```
docker build -t local/spring-boot-demo:latest .
```

## Deploy to K8S platform
### Create a deployment and service running this app.
```
# cat spring-demo.yaml
apiVersion: v1
kind: Service
metadata:
  labels:
    run: spring-demo
  name: spring-demo
spec:
  ports:
  - name: spring
    port: 8080
    protocol: TCP
    targetPort: 8080
  selector:
    run: spring-demo
  type: NodePort
---
apiVersion: extensions/v1beta1
kind: Deployment
metadata:
  labels:
    run: spring-demo
  name: spring-demo
spec:
  replicas: 1
  selector:
    matchLabels:
      run: spring-demo
  template:
    metadata:
      labels:
        run: spring-demo
    spec:
      containers:
      - image: local/spring-boot-demo:latest
        imagePullPolicy: IfNotPresent
        name: spring-demo
        ports:
        - containerPort: 8080
          protocol: TCP
```
```
# kubectl create -f spring-demo.yaml
service "spring-demo" created
deployment "spring-demo" created
```
### Create tls secret
```
# cat create-secret.sh
#!/bin/bash
openssl genrsa -out ing-tls.key 4096
openssl req -new -key ing-tls.key -out ing-tls.csr -subj "/CN=haoqing-ubu-1.eng.platformlab.ibm.com"
openssl x509 -req -days 36500 -in ing-tls.csr -signkey ing-tls.key -out ing-tls.crt
kubectl create secret tls ing-tls-secret --cert=ing-tls.crt --key=ing-tls.key
```
```
./create-secret.sh
```
### Create an ingress
```
# cat spring-demo-ing-tls.yaml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: spring-demo
  annotations:
    ingress.kubernetes.io/rewrite-target: /
    ingress.kubernetes.io/ssl-redirect: "true"
spec:
  rules:
  - host: haoqing-ubu-1.eng.platformlab.ibm.com
    http:
      paths:
      - path: /
        backend:
          serviceName: spring-demo
          servicePort: 8080
  tls:
  - hosts:
    - haoqing-ubu-1.eng.platformlab.ibm.com
    secretName: ing-tls-secret
```
```
# kubectl create -f spring-demo-ing-tls.yaml
ingress "spring-demo" created
```
### Access app from browser
Check User List
```
https://haoqing-ubu-1.eng.platformlab.ibm.com/users/
```
Create User, and redirect to "User List" page
```
https://haoqing-ubu-1.eng.platformlab.ibm.com/users/create
```
