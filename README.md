# users-api



#REQUESTS:

			.) Get all users from the database
					GET - localhost:8087/users/



			.) Get an user from the database searching by username
					GET - localhost:8087/users/getByUsername/{username}



			.) Get an user from the database searching by email
					GET - localhost:8087/users/getByEmail/{email}



			.) Creates an user
					POST - localhost:8087/users/
					
					Body:
					{
    						"username":"jcvina",
    						"firstName": "juan",
						    "lastName": "cruz",
						    "email": "jcvinaTEST@gmail.com",
						    "gender": "m",
						    "password": "123456",
						    "birthDate": [
						        2018,
						        7,
						        7
						    ]
					}



			.) Deletes an user
					DELETE - localhost:8087/users/delete/{username}



			.) Updates an user
					PUT - localhost:8087/users/update/{username}

					Body:
					{
					    "username":"jcfervina",
					    "firstName": "juan",
					    "lastName": "cruz",
					    "email": "jcfervinaTEST@gmail.com",
					    "gender": "m",
					    "password": "123456",
					    "birthDate": [
					        2018,
					        7,
					        7
					    ]
					}