package wang.willard.source.asm.blog;

public class ReaderAndWriterTest {

    public static void main(String[] args) {
        CustomClassWriter ccw = new CustomClassWriter();
        ccw.publicizeMethod();
    }
}
