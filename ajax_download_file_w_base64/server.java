/**
 *
 * @author Lam Du Thach
 */
@WebServlet(name = "Test", urlPatterns = { "/downloadFile" })
public class Test extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        File file = new File("/path/to/test/file");
        String fileBase64 = encodeFileToBase64(file);
        file.delete();
        PrintWriter out = response.getWriter();
        out.print(fileBase64);
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
}