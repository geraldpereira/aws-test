aws-test-rest
=============

REST Web Services module. 

- pom.xml : jetty-maven-plugin is used to run the application on local machine. 
	+ Start 'jetty:run' with -Dproperties.credentials=/path/to/credentials.properties VM option
- jetty.xml : configuration of the local jetty server
- aws-test.xml : jetty context file

keytools (deprecated)
--------------------
To create JETTY SSL certificate run (http://wiki.eclipse.org/Jetty/Howto/Configure_SSL):
keytools -keystore keystore -alias jetty -genkey -keyalg RSA
keytools -certreq -alias jetty -keystore keystore -file jetty.csr


SSL
---
As simple as maven ...

Execute the following commands on an EC2 AWS linux instance to generate the SSL certificates.

http://www.akadia.com/services/ssh_test_certificate.html

openssl pkcs12 -inkey jetty.key -in jetty.crt -export -out jetty.pkcs12

1. Generate a Private Key
openssl genrsa -des3 -out server.key 1024

2. Generate a CSR (Certificate Signing Request)
openssl req -new -key server.key -out server.csr

/!\ Common Name (eg, your name or your server's hostname) []: loadbalancer.domain.name (aws-test.byob.fr)

3. Remove Passphrase from Key
cp server.key server.key.org
openssl rsa -in server.key.org -out server.key

4. Generate a Self-Signed Certificate
openssl x509 -req -days 365 -in server.csr -signkey server.key -out server.crt

5. Load Keys and Certificates via PKCS12 
http://wiki.eclipse.org/Jetty/Howto/Configure_SSL#Loading_Keys_and_Certificates_via_PKCS12
openssl pkcs12 -inkey server.key -in server.crt -export -out server.pkcs12

6. Import in a keystore 
keytool -importkeystore -srckeystore server.pkcs12 -srcstoretype PKCS12 -destkeystore keystore

7. Download the files
- server.key.org : Private Key, password protected 
- server.key : Private Key
- server.csr : Certificate Signing Request
- server.crt : Self-Signed Certificate
- server.pkcs12 : PKCS12 format file (key + certificate)
- keystore : java keystore

8. Jetty
- add the connector in jetty.xml (see $JETTY_HOME/etc/jetty-ssl.xml for a example)
- Change the keystore password : it can be obfuscated with Jetty tools http://docs.codehaus.org/display/JETTY/Securing+Passwords

9. AWS Elastic Load Balancer
In the AWS Console > EC2 > Network & Security > Load Balancers > Listeners
Add a HTTPS to HTTPS listener, in the SSL certificate form, select a name and Copy/Paste
- the content of server.key in the Private Key field (must not be password protected)
- the content of server.crt in the Public Key Certificate field
- leave the certificate chain field blank
 