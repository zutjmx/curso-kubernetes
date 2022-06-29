# curso-kubernetes: Primera aplicación spring para el curso SpringBoot con Docker y Kubernetes.

# Crear imagen de microservicio Cursos
docker build -t micro-svr-cursos . -f .\mcsv-cursos\Dockerfile

# Crear imagen de microservicio Usuarios
docker build -t micro-svr-usuarios . -f .\mcsv-usuarios\Dockerfile

# Levantar microservicio Usuarios
docker run -p 8001:8001 -d --rm --name mcsv-usuarios --network spring micro-svr-usuarios

# Levantar microservicio Cursos
docker run -p 8002:8002 -d --rm --name mcsv-cursos --network spring micro-svr-cursos
