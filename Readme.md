Video Streaming API
===================

This API provides endpoints for managing video uploads, retrieving video metadata, and streaming videos. It is implemented using Spring Boot, and supports video file storage, metadata management, and efficient streaming.

Endpoints
---------

### 1\. Upload Video

*   **Endpoint**: `/api/v1/videos`
*   **Method**: POST
*   **Description**: Uploads a new video.
*   **Parameters**:
    *   `file` (MultipartFile): The video file to upload.
    *   `title` (String): The title of the video.
    *   `description` (String): The description of the video.
*   **Responses**:
    *   `200 OK`: Returns the saved video object if the upload was successful.
    *   `500 Internal Server Error`: Returns an error message if the upload failed.

**Example Request**:

    curl -X POST "http://localhost:8080/api/v1/videos" \
      -F "file=@path/to/video.mp4" \
      -F "title=Sample Video" \
      -F "description=This is a sample video."

### 2\. Get All Videos

*   **Endpoint**: `/api/v1/videos`
*   **Method**: GET
*   **Description**: Retrieves a list of all uploaded videos.
*   **Responses**:
    *   `200 OK`: Returns a list of video objects.

**Example Request**:

    curl -X GET "http://localhost:8080/api/v1/videos"

### 3\. Stream Video

*   **Endpoint**: `/api/v1/videos/stream/{videoId}`
*   **Method**: GET
*   **Description**: Streams a video by its ID.
*   **Parameters**:
    *   `videoId` (String): The ID of the video to stream.
*   **Responses**:
    *   `200 OK`: Streams the video content.
    *   `404 Not Found`: If the video is not found.

**Example Request**:

    curl -X GET "http://localhost:8080/api/v1/videos/stream/{videoId}"

### 4\. Stream Video in Chunks

*   **Endpoint**: `/api/v1/videos/stream/range/{videoId}`
*   **Method**: GET
*   **Description**: Streams a video in chunks to support partial content delivery and seeking.
*   **Parameters**:
    *   `videoId` (String): The ID of the video to stream.
    *   `Range` (Header, optional): Specifies the byte range to stream.
*   **Responses**:
    *   `206 Partial Content`: Returns the requested byte range of the video.
    *   `404 Not Found`: If the video is not found.
    *   `500 Internal Server Error`: If there is an error processing the request.

**Example Request**:

    curl -X GET "http://localhost:8080/api/v1/videos/stream/range/{videoId}" \
      -H "Range: bytes=0-1024000"

Flow of Chunks Response Headers
-----------------------

<img src="https://res-console.cloudinary.com/dth5ysuhs/media_explorer_thumbnails/1bca50dabdfe82e25ebddaa6b1081459/detailed" width="5000">

Dependencies
------------

*   Spring Boot
*   Spring Web
*   Spring Boot Starter for file handling

Configuration
-------------

Ensure that your application is configured properly for file storage. Update your Spring Boot application properties to set the correct file storage path and any additional configurations required for handling video files.

Notes
-----

*   The `streamVideoRange` method supports HTTP range requests, which allows for video seeking and partial content delivery.
*   Implement thorough error handling and logging to track and address issues in a production environment.

License
-------

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.