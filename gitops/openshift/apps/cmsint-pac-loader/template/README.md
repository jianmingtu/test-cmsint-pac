## Templates to create openshift components related to cmsint-pac-loader api deployment

### Command to execute template
1) Login to OC using login command
2) Run below command in each env. namespace dev/test/prod
   ``oc process -f cmsint-pac-loader.yaml --param-file=cmsint-pac-loader.env | oc apply -f -``
