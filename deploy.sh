#!/bin/bash

# Directory where you want to store the project
PROJECT_DIR="/srv"

# GitHub repository URL
GITHUB_REPO="https://github.com/orlandolorenzo724/mailjet.git"

# Check if the project directory already exists
if [ -d "$PROJECT_DIR" ]; then
    echo "Project directory already exists. Pulling the latest changes..."
    # Change to the project directory
    cd $PROJECT_DIR/mailjet
    # Pull the latest changes from the GitHub repository
    git pull
else
    # Clone the GitHub repository to the specified directory
    git clone $GITHUB_REPO $PROJECT_DIR
    # Change to the project directory
    cd $PROJECT_DIR/mailjet
fi

# Clean and package the Maven project
mvn clean package

# Build the Docker image with a specified tag
docker build -t "kreyzon-mailjet:latest" .

# Exit the script and provide instructions for running Docker Compose
echo "Maven build and Docker image creation complete."
echo "Now, you can run Docker Compose to start the application."

# Optional: Provide Docker Compose command for user reference
echo "To run Docker Compose, execute the following command:"
echo "docker-compose up -d"
