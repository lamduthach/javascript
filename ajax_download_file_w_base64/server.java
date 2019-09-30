/**
 *
 * @author Lam Du Thach
 */
@WebServlet(name = "Test", urlPatterns = { "/downloadFile" })
public class Test extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        PrintWriter out = null;
        try {
            File file = new File("/path/to/test/file");
            String fileBase64 = encodeFileToBase64(file);
            file.delete();
            out = response.getWriter();
            out.print(fileBase64);
        } catch (IOException ex) {
            // if something happen
            out.print(new CustomException(ErrorType.ERROR_RUNNING, "Oops!!! Something happen for server"));
        } finally {
            out.close();
        }
    }

    private String encodeFileToBase64(File originalFile) throws IOException {
        if (!originalFile.exists() || !originalFile.isFile()) {
            throw new IOException("File not found");
        }
        try (FileInputStream fileInputStreamReader = new FileInputStream(originalFile)) {
            byte[] bytes = new byte[(int) originalFile.length()];
            fileInputStreamReader.read(bytes);
            return new String(Base64.encodeBase64(bytes));
        }
    }

    public class CustomException extends Exception {

        private final ErrorType errorType;
        private final String message;

        public CustomException(ErrorType errorType, String message) {
            super(message);
            this.errorType = errorType;
            this.message = message;
        }

        public String getJsonString() {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("errorType", errorType.name());
            jsonObject.addProperty("message", message);
            return jsonObject.toString();
        }
    }
}
