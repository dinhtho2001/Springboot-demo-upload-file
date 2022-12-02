package com.example.demo.payload.Response;

public class FileUploadResponse {
	private String fileName;
    private String downloadUri;
    private long size;
    private String filePath;
    public FileUploadResponse() {
    	
    }
    	
	public FileUploadResponse(String fileName, String downloadUri, long size, String filePath) {
		super();
		this.fileName = fileName;
		this.downloadUri = downloadUri;
		this.size = size;
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDownloadUri() {
		return downloadUri;
	}
	public void setDownloadUri(String downloadUri) {
		this.downloadUri = downloadUri;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
      
 
}
