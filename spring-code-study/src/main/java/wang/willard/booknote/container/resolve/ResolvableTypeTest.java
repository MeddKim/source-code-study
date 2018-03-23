package wang.willard.booknote.container.resolve;

import org.springframework.core.ResolvableType;

/**
 * ResolvableType是Spring提供的泛型操作支持（例如获取泛型的实际类型）。
 */
public class ResolvableTypeTest {

    public static void main(String[] args) {
        //得到泛型信息
        IService iervice = new ABService();
        ResolvableType type = ResolvableType.forClass(iervice.getClass());
        System.out.println(type.getInterfaces()[0].getGeneric(0).resolve());
        System.out.println(type.getInterfaces()[0].getGeneric(1).resolve());  //getGenerc得到某个位置的泛型，resolve实际解析出泛型
    }
}


