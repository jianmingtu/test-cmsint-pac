## Templates to create openshift components related to cmsint-pac-transformer api deployment

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod
   ``oc process -f cmsint-pac-transformer.yaml --param-file=cmsint-pac-transformer.env | oc apply -f -``
