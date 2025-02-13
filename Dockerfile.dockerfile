# Use an Nginx image to serve the static site
FROM nginx:latest

# Copy Bootstrap project files to Nginx web directory
COPY . /usr/share/nginx/html

# Expose port 80 for web traffic
EXPOSE 80

# Start Nginx server
CMD ["nginx", "-g", "daemon off;"]
