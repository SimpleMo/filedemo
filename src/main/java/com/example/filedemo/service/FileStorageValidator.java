package com.example.filedemo.service;

import com.example.filedemo.exception.ValidationException;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface FileStorageValidator {

	Pattern validationPattern = Pattern.compile("\\.pdf|\\.txt|\\.png|\\.gif|\\.jpg|\\.jpeg|\\.doc|\\.docx ");

	default void validateFile(MultipartFile file) {

		if (file.getSize() > 15_000_000L)
			throw new ValidationException("File size must be less than 15 MB");

		Matcher validationMatcher = validationPattern.matcher(Objects.requireNonNull(file.getOriginalFilename()));
		if (!validationMatcher.find())
			throw new ValidationException("This type of file is not supported: " + file.getContentType());

	}
}
