package co.edu.unicundi.proyectoSpringPrueba.dto;

import java.time.LocalDateTime;

public class ExceptionResponse {
	//private LocalDateTime timestamp;
	
		private String timestamp;
		
		private String status;
		
		private String error;
		
		private String message;
		
		private String path;

		public ExceptionResponse(String status, String error, String message, String path) {
			super();
			this.timestamp = LocalDateTime.now().toString();
			this.status = status;
			this.error = error;
			this.message = message;
			this.path = path;
		}

		public String getTimestamp() {
			return timestamp;
		}

		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}

		public String getStatus() {
			return status;
		}

		public void setStatus(String status) {
			this.status = status;
		}

		public String getError() {
			return error;
		}

		public void setError(String error) {
			this.error = error;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
}
