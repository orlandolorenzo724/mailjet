# mailjet
Mailjet API implementation for sending emails with HTML code to multiple receivers

# Automated Project Deployment Script

This script is designed to automate the process of downloading a project from a GitHub repository, building it with Maven, creating a Docker image, and running it with Docker Compose. It also includes the ability to stop and remove an existing Docker container if one is already running.

## Prerequisites

Before using this script, make sure you have the following prerequisites installed on your system:

- Git
- Maven
- Docker
- Docker Compose

## Usage

1. Clone this repository or copy the script to your local machine.

2. Open the script using a text editor of your choice.

3. Update the following variables at the beginning of the script to customize it for your project:

   - `PROJECT_DIR`: The directory where you want to store the project.
   - `GITHUB_REPO`: The URL of the GitHub repository you want to clone.
   - `DOCKER_IMAGE_NAME`: The name for the Docker image you want to create.
   - `DOCKER_CONTAINER_NAME`: The name for the Docker container.
   
4. Save your changes.

5. Open a terminal window and navigate to the directory where the script is located.

6. Make the script executable by running the following command:
   ```bash
   chmod +x deploy.sh
   ```
7. Execute the script by running the following command:
   ```bash
   ./deploy.sh
   ```
   
The script will perform the following steps:

- Check if the project directory already exists. If it does, it will pull the latest changes from the GitHub repository.
- If the project directory does not exist, it will clone the GitHub repository.
- Change to the project directory.
- Clean and package the Maven project.
- Build a Docker image with the specified name.
- Check if a Docker container with the same name is already running. If so, it will stop and remove it.
- Start a new Docker container using Docker Compose in detached mode.

8. Monitor the script's output for any errors or issues.

## Notes

- You can customize the script further to meet your specific project requirements.

- Ensure that you have the necessary permissions to pull from the GitHub repository and create Docker containers.

- Make sure to set up your `application.yml` or other configuration files as needed for your project.

- This script is provided as-is and may require additional modifications to work with specific projects and environments.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

