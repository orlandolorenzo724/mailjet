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

## Configure docker-compose.yml for connecting to Mailjet API

In order to connect to Mailjet API, you need to configure some information in the docker-compose.yml file

```yml
version: '3'
services:
  kreyzon-mailjet-container:
    image: kreyzon-mailjet
    ports:
      - "9090:9090"
    environment:
      - LOG_PATH=/app/logs
      - MAILJET_API_KEY=YOUR_MAILJET_API_KEY
      - MAILJET_SECRET_KEY=YOUR_MAILJET_SECRET_KEY
      - MAILJET_EMAIL=YOUR_MAILJET_EMAIL
      - MAILJET_NAME=YOUR_MAILJET_NAME
```

In the docker-compose.yml file:

- `image`: Specifies the Docker image for your `kreyzon-mailjet` container. Ensure that you've built the Docker image with the correct application code and dependencies before using this.

- `ports`: Maps port 9090 from your container to port 9090 on your host machine. You can change the host port if needed.

- `environment`: This section allows you to set environment variables for your container. Replace the placeholders with your actual Mailjet API key, secret key, email, and name.

   - `MAILJET_API_KEY`: Set this to your Mailjet API key.
   - `MAILJET_SECRET_KEY`: Set this to your Mailjet secret key.
   - `MAILJET_EMAIL`: Set this to your Mailjet email address.
   - `MAILJET_NAME`: Set this to your Mailjet name.

Make sure to replace `YOUR_MAILJET_API_KEY`, `YOUR_MAILJET_SECRET_KEY`, `YOUR_MAILJET_EMAIL`, and `YOUR_MAILJET_NAME` with your actual Mailjet credentials and information.


After updating the docker-compose.yml file, you can run docker-compose up -d to start the kreyzon-mailjet container with the specified configuration and environment variables.

## API Endpoint: POST to /kreyzon/api/v1/mailjet

### Description
This API endpoint allows you to send emails using the Mailjet service. You can send one or more email messages in a single request by providing a list of email requests in the JSON format.

### Request
- Method: POST
- URL: `/kreyzon/api/v1/mailjet`

#### Request Body
The request body should contain a JSON array of email requests. Each email request should have the following fields:
- `receiver`: The email address of the recipient.
- `subject`: The subject of the email.
- `text`: The HTML content of the email message.

Example Request Body:
```json
[
  {
    "receiver": "email1@hotmail.com",
    "subject": "Test Subject 1",
    "text": "<!DOCTYPE html> <!-- HTML content here -->"
  },
  {
    "receiver": "email2@gmail.com",
    "subject": "Test Subject 2",
    "text": "<!DOCTYPE html> <!-- HTML content here -->"
  }
]
```

### Response Body
The response body will look like this, if everything is ok.

```json
{
    "status": "OK",
    "message": "Email sent to all receivers",
    "data": [
        {
            "receiver": "email1@hotmail.com",
            "subject": "Test Subject 1",
            "text": "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Email Example</title></head><body><table style=\"width:100%; max-width:600px; margin:0 auto; font-family: Arial, sans-serif; border-collapse: collapse;\"><tr><td style=\"background-color:#f2f2f2; padding: 20px; text-align:center;\"><h1>Welcome to Our Newsletter</h1><p>Stay updated with our latest news and offers.</p></td></tr><tr><td style=\"padding: 20px;\"><h2>Featured Article</h2><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus.</p><a href=\"https://example.com/article\" style=\"text-decoration:none; color:#007BFF;\">Read More</a></td></tr><tr><td style=\"background-color:#007BFF; color:#ffffff; text-align:center; padding:10px;\">&copy; 2023 Your Company. All rights reserved.</td></tr></table></body></html>"
        },
        {
            "receiver": "email2@gmail.com",
            "subject": "Test Subject 1",
            "text": "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Email Example</title></head><body><table style=\"width:100%; max-width:600px; margin:0 auto; font-family: Arial, sans-serif; border-collapse: collapse;\"><tr><td style=\"background-color:#f2f2f2; padding: 20px; text-align:center;\"><h1>Welcome to Our Newsletter</h1><p>Stay updated with our latest news and offers.</p></td></tr><tr><td style=\"padding: 20px;\"><h2>Featured Article</h2><p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur non nulla sit amet nisl tempus convallis quis ac lectus.</p><a href=\"https://example.com/article\" style=\"text-decoration:none; color:#007BFF;\">Read More</a></td></tr><tr><td style=\"background-color:#007BFF; color:#ffffff; text-align:center; padding:10px;\">&copy; 2023 Your Company. All rights reserved.</td></tr></table></body></html>"
        }
    ]
}
```

If some emails (could be all) could not be sent, the response will look like this
```json
{
    "status": "ERROR",
    "message": "Email not sent to some receivers",
    "data": [
        "email1@hotmail.com",
        "email2@gmail.com"
    ]
}
```


## Notes

- You can customize the script further to meet your specific project requirements.

- Ensure that you have the necessary permissions to pull from the GitHub repository and create Docker containers.

- Make sure to set up your `application.yml` or other configuration files as needed for your project.

- This script is provided as-is and may require additional modifications to work with specific projects and environments.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

