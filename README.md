# This is simple spring boot backend for a restaurant location service

pull this image using this command
`docker pull adij69/restaurant-backend:latest`

And run it with this command
`docker run -e SPRING_DATA_MONGODB_URI='<your-mongo-atlas uri goes here>' -p 8080:8080 restaurant-backend:latest`