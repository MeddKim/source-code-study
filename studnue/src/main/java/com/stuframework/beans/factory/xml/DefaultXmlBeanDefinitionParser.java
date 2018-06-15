package com.stuframework.beans.factory.xml;

import com.stuframework.beans.MutablePropertyValues;
import com.stuframework.beans.PropertyValue;
import com.stuframework.beans.factory.support.AbstractBeanDefinition;
import com.stuframework.beans.factory.support.BeanDefinitionRegistry;
import com.stuframework.core.io.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DefaultXmlBeanDefinitionParser implements XmlBeanDefinitionParser {

    //bean名称分割符
//    public static final String BEAN_NAME_DELIMITERS = ",; ";
    public static final String BEAN_NAME_DELIMITERS = ",";

    public static final String TRUE_VALUE = "true";
    public static final String DEFAULT_VALUE = "default";

    public static final String DEFAULT_LAZY_INIT_ATTRIBUTE = "default-lazy-init";   //是否默认延迟初始化
    public static final String DEFAULT_DEPENDENCY_CHECK_ATTRIBUTE = "default-dependency-check";
    public static final String DEFAULT_AUTOWIRE_ATTRIBUTE = "default-autowire";

    public static final String BEAN_ELEMENT = "bean";
    public static final String DESCRIPTION_ELEMENT = "description";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String PARENT_ATTRIBUTE = "parent";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String SINGLETON_ATTRIBUTE = "singleton";
    public static final String DEPENDS_ON_ATTRIBUTE = "depends-on";
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";
    public static final String CONSTRUCTOR_ARG_ELEMENT = "constructor-arg";
    public static final String INDEX_ATTRIBUTE = "index";
    public static final String TYPE_ATTRIBUTE = "type";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String REF_ELEMENT = "ref";
    public static final String IDREF_ELEMENT = "idref";
    public static final String BEAN_REF_ATTRIBUTE = "bean";
    public static final String LOCAL_REF_ATTRIBUTE = "local";
    public static final String LIST_ELEMENT = "list";
    public static final String SET_ELEMENT = "set";
    public static final String MAP_ELEMENT = "map";
    public static final String KEY_ATTRIBUTE = "key";
    public static final String ENTRY_ELEMENT = "entry";
    public static final String VALUE_ELEMENT = "value";
    public static final String NULL_ELEMENT = "null";
    public static final String PROPS_ELEMENT = "props";
    public static final String PROP_ELEMENT = "prop";

    public static final String LAZY_INIT_ATTRIBUTE = "lazy-init";

    public static final String DEPENDENCY_CHECK_ATTRIBUTE = "dependency-check";
    public static final String DEPENDENCY_CHECK_ALL_ATTRIBUTE_VALUE = "all";
    public static final String DEPENDENCY_CHECK_SIMPLE_ATTRIBUTE_VALUE = "simple";
    public static final String DEPENDENCY_CHECK_OBJECTS_ATTRIBUTE_VALUE = "objects";

    public static final String AUTOWIRE_ATTRIBUTE = "autowire";
    public static final String AUTOWIRE_BY_NAME_VALUE = "byName";
    public static final String AUTOWIRE_BY_TYPE_VALUE = "byType";
    public static final String AUTOWIRE_CONSTRUCTOR_VALUE = "constructor";
    public static final String AUTOWIRE_AUTODETECT_VALUE = "autodetect";

    protected final Log logger = LogFactory.getLog(getClass());

    private BeanDefinitionRegistry beanFactory;

    private ClassLoader beanClassLoader;

    private Resource resource;

    private String defaultLazyInit;

    private String defaultDependencyCheck;

    private String defaultAutowire;


    @Override
    public void registerBeanDefinitions(BeanDefinitionRegistry beanFactory, ClassLoader beanClassLoader, Document doc, Resource resource) {
        this.beanFactory = beanFactory;
        this.beanClassLoader = beanClassLoader;
        this.resource = resource;

        logger.debug("bean定义加载开始....");
        Element root = doc.getDocumentElement();

        this.defaultLazyInit = root.getAttribute(DEFAULT_LAZY_INIT_ATTRIBUTE);
        this.defaultLazyInit = root.getAttribute(DEFAULT_DEPENDENCY_CHECK_ATTRIBUTE);
        this.defaultAutowire = root.getAttribute(DEFAULT_AUTOWIRE_ATTRIBUTE);

        NodeList nodeList = root.getChildNodes();
        int beanDefinitionCounter = 0;
        for(int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node instanceof Element && BEAN_ELEMENT.equals(node.getNodeName())){
                beanDefinitionCounter ++;
                //从元素中加载bean
                loadBeanDefinition((Element)node);
            }
        }
    }

    /**
     * 从配置文件（resource中加载Bean配置）
     * 最后的结果会加载到  AbstractBeanFactory的
     *       private final Map aliasMap = Collections.synchronizedMap(new HashMap());
     * @param ele
     */
    protected void loadBeanDefinition(Element ele){
        String id = ele.getAttribute(ID_ATTRIBUTE);
        String nameAttr = ele.getAttribute(NAME_ATTRIBUTE);
        List<String> aliases = new ArrayList<>();
        if(null != nameAttr && !"".equals(nameAttr)){
            String[] nameArr = nameAttr.split(BEAN_NAME_DELIMITERS);
            aliases.addAll(Arrays.asList(nameArr));
        }
        //未定义id，使用别名的第一个作为ID
        if(null != id || "".equals(id) && !aliases.isEmpty()){
            id = aliases.get(0);
        }

        AbstractBeanDefinition beanDefinition = parseBeanDefinition(ele, id);

    }

    /**
     * 获取Bean配置包装  （从  <Bean ></>）
     * @param ele
     * @param beanName
     * @return
     */
    protected AbstractBeanDefinition parseBeanDefinition(Element ele, String beanName){
        String className = null;
        if(ele.hasAttribute(NAME_ATTRIBUTE)){
            className = ele.getAttribute(NAME_ATTRIBUTE);
        }
        String parent = null;
        if(ele.hasAttribute(PARENT_ATTRIBUTE)){
            parent = ele .getAttribute(PARENT_ATTRIBUTE);
        }
        if(null == className && null == parent){
//            throw new BeanDefinitionStoreException(this.resource, beanName, "Either 'class' or 'parent' is required");
        }
        AbstractBeanDefinition bd = null;
        MutablePropertyValues pvs = getPropertyValueSubElements(beanName,ele);
        return null;
    }

    /**
     * 获取一个<bean></bean>中所有<property></property>配置信息
     * @param beanName
     * @param beanEle  xml配置中一个<bean/> 节点
     * @return
     */
    protected MutablePropertyValues getPropertyValueSubElements(String beanName, Element beanEle){
        NodeList nodeList = beanEle.getChildNodes();
        MutablePropertyValues pvs = new MutablePropertyValues();
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            if(node instanceof Element && PROPERTY_ELEMENT.equals(node.getNodeName())){
                //解析bean的一个property标签
                parsePropertyElement(beanName, pvs, (Element) node);
            }
        }
        return pvs;
    }

    /**
     * <property name="propertyName" value="value"/> 元素的解析规则
     *  property =>
     * @param beanName
     * @param pvs
     * @param ele 一个<property/>标签
     * @throws DOMException
     */
    protected void parsePropertyElement(String beanName, MutablePropertyValues pvs, Element ele)
            throws DOMException {
        String propertyName = ele.getAttribute(NAME_ATTRIBUTE);
        if ("".equals(propertyName)) {
//            throw new BeanDefinitionStoreException(this.resource, beanName,
//                    "标签 'property' 必须有一个 'name' 属性");
        }
        Object val = getPropertyValue(ele, beanName);
        pvs.addPropertyValue(new PropertyValue(propertyName, val));
    }

    protected Object getPropertyValue(Element ele, String beanName){
        //目前只支持 value, ref, collection
        NodeList nl = ele.getChildNodes();
        Element valueRefOrCollectionElement = null;
        for (int i = 0; i < nl.getLength(); i++) {
            Element candidateEle = (Element) nl.item(i);
            if(DESCRIPTION_ELEMENT.equals(candidateEle.getTagName())){
                //
            }else {

            }
        }
        return null;
    }


}
