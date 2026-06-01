# Deploy në OpenShift - Komanda

## 1. Login në OpenShift
```bash
oc login --token=<your-token> --server=<openshift-server-url>
```

## 2. Krijo një project të ri
```bash
oc new-project student-management
```

## 3. Build Docker image
```bash
# Ndërto imazhin
docker build -t student-management:1.0.0 .

# Tag imazhin për OpenShift registry
docker tag student-management:1.0.0 <openshift-registry>/student-management/student-management:1.0.0

# Push në registry
docker push <openshift-registry>/student-management/student-management:1.0.0
```

## 4. Ose përdor Source-to-Image (S2I) në OpenShift
```bash
# Krijo build config nga source code
oc new-build --name=student-management \
  --strategy=docker \
  --binary=true

# Start build
oc start-build student-management --from-dir=. --follow
```

## 5. Deploy resources në OpenShift
```bash
# Apply secret
oc apply -f openshift/secret.yaml

# Apply configmap
oc apply -f openshift/configmap.yaml

# Apply deployment dhe service
oc apply -f openshift/deployment.yaml
```

## 6. Shiko statusin e deployment
```bash
# Shiko pods
oc get pods

# Shiko logs
oc logs -f deployment/student-management

# Shiko services dhe routes
oc get svc
oc get routes
```

## 7. Akseso aplikacionin
```bash
# Merr URL e route
oc get route student-management-route -o jsonpath='{.spec.host}'

# Testo aplikacionin
curl https://<route-url>/api/students
```

## 8. Scale aplikacionin
```bash
# Scale në 3 replicas
oc scale deployment student-management --replicas=3
```

## 9. Update aplikacionin
```bash
# Build version i ri
oc start-build student-management --from-dir=. --follow

# Rollout ri
oc rollout restart deployment/student-management

# Shiko statusin e rollout
oc rollout status deployment/student-management
```

## 10. Cleanup
```bash
# Fshi deployment
oc delete -f openshift/deployment.yaml

# Fshi project
oc delete project student-management
```
