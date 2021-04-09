public class OverrideTest {
    private int id;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "OverrideTest{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}