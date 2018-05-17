INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-trusted-client',
'',
'',
'read,write,trust',
'password,authorization_code,refresh_token,implicit',
'',
'ROLE_CLIENT, ROLE_TRUSTED_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'');

INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-trusted-client-with-secret',
'',
'somesecret',
'read,write,trust',
'password,authorization_code,refresh_token,implicit',
'',
'ROLE_CLIENT, ROLE_TRUSTED_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'');

INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-client-with-secret',
'',
'secret',
'read',
'client_credentials',
'',
'ROLE_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'');

INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-less-trusted-client',
'',
'',
'',
'authorization_code,implicit',
'http://anywhere?key=value',
'ROLE_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'');

INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-client-with-registered-redirect',
'',
'',
'read,trust',
'authorization_code,client_credentials',
'http://anywhere?key=value',
'ROLE_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'');

INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-less-trusted-autoapprove-client',
'',
'',
'read,write,trust',
'implicit',
'',
'ROLE_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'true');

INSERT INTO `siaamdb`.`oauth_client_details`
(`client_id`,
`resource_ids`,
`client_secret`,
`scope`,
`authorized_grant_types`,
`web_server_redirect_uri`,
`authorities`,
`access_token_validity`,
`refresh_token_validity`,
`additional_information`,
`autoapprove`)
VALUES
('my-untrusted-client-with-registered-redirect',
'',
'',
'read',
'authorization_code',
'http://anywhere',
'ROLE_CLIENT',
60,
60000,
'{ "addInfo" : "someInfo" }',
'true');
