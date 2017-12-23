package utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import config.DsvBean;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Analysis {
    public static void main(String[] args)
            throws ParserConfigurationException, SAXException, IOException {
        ArrayList<String> dsvBeanList = analysisXml();
        System.out.println(dsvBeanList);
    }

    public static ArrayList<String> analysisXml() {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            String xmlUrl = System.getProperty("user.dir") + "..\\webapps\\";
//            Document document = db.parse("src/config/dsv.xml");
            Document document = db.parse(xmlUrl);
            NodeList users = document.getChildNodes();

            ArrayList<String> interfaceNames = new ArrayList<>();

//            List<DsvBean> dsvBeanList = new ArrayList<DsvBean>();
            DsvBean dsvBean = new DsvBean();

            for (int i = 0; i < users.getLength(); i++) {
                Node user = users.item(i);
                NodeList userInfo = user.getChildNodes();

                System.out.println("i:" + i + user + userInfo);
                for (int j = 0; j < userInfo.getLength(); j++) {
                    Node node = userInfo.item(j);
                    NodeList userMeta = node.getChildNodes();

                    System.out.println("j:" + j + node + userMeta);
                    for (int k = 0; k < userMeta.getLength(); k++) {
                        if (userMeta.item(k).getNodeName() != "#text") {
                            interfaceNames.add(userMeta.item(k).getTextContent());
//                            dsvBean = buildDsvBean(userMeta, k);
//                            dsvBeanList.add(dsvBean);
//                            System.out.println("dsvBeanList:" + dsvBeanList);
                            System.out.println("k:" + k + userMeta.item(k).getNodeName()
                                    + ":" + userMeta.item(k).getTextContent());
                        }
                    }
                    System.out.println();
                }
            }
            boolean aa = interfaceNames.contains("AddBolg");
            return interfaceNames;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static DsvBean buildDsvBean(NodeList userMeta, int k) {
        try {
            DsvBean dsvBean = new DsvBean();

            String name = userMeta.item(k).getNodeName();

            String nameUp = name.substring(0, 1).toUpperCase() + name.substring(1); //将属性的首字符大写，方便构造get，set方法

            Method m = dsvBean.getClass().getMethod("set" + nameUp, new Class[]{String.class});

            m.invoke(dsvBean, new Object[]{userMeta.item(k).getTextContent()});

            return dsvBean;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }
}
