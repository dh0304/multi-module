package com.cafegory.auth.infrastructure.aws;

import com.cafegory.auth.util.ImageData;

public interface AwsS3Client {

	void uploadImageToS3(String filename, ImageData imageDate);

	String getUrl(String filename);
}
