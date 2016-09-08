package edu.unsw.comp9321.util;


import edu.unsw.comp9321.Constant;
import edu.unsw.comp9321.entity.Article;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.XMLOutputter;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by Langley on 8/26/16.
 */
public class XMLConverter {

    private final static String DBLP_PATH = "/Users/Langley/Downloads/dblp.xml";

    public XMLConverter() {
    }

    public static void main(String[] args) throws Exception {

        // step 1: SAX解析器工厂实例
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // step 2: SAX解析器实例
        SAXParser parser = factory.newSAXParser();

        // step 3: 开始进行解析
        DBLPSaxHandler dblpSaxHandler = new DBLPSaxHandler(Constant.MAX_XML_COUNT);

        try {
            parser.parse(new File(DBLP_PATH), dblpSaxHandler);
        } catch (Exception e) {

        }

        List<Article> articles = dblpSaxHandler.getArticles();
        System.out.println("articles counts: " + articles.size());

        writeDOM(articles);
    }

    public static void writeDOM(List<Article> articles) {
        //定义一个root作为xml文档的根元素
        Element root = new Element("dblp");
        //生成一个文档
        Document Doc = new Document(root);
        for (Article article : articles) {
            // Set lab name.
            Element elements = new Element("article");

            // Set attributes.
            elements.setAttribute("mdate", "" + article.getMdate());
            elements.setAttribute("key", "" + article.getKey());

            //在cdr标签内部添加新的元素，即cdr的下一级标签，标签属性名为username,值为ss
            elements.addContent(new Element("author").setText(article.getAuthor()));
            elements.addContent(new Element("title").setText(article.getTitle()));
            elements.addContent(new Element("journal").setText(article.getJournel()));
            elements.addContent(new Element("volume").setText(article.getVolume()));
            elements.addContent(new Element("year").setText(article.getYear()));
            elements.addContent(new Element("pages").setText(article.getPages()));
            elements.addContent(new Element("url").setText(article.getUrl()));
            elements.addContent(new Element("ee").setText(article.getEe()));

            root.addContent(elements);

        }
        //定义一个用于输出xml文档的类
        XMLOutputter XMLOut = new XMLOutputter();

        try {
            File file = new File(Constant.XML_SAVE_PATH);
//            if (!file.exists()) {
            file.createNewFile();
//            }
            XMLOut.output(Doc, new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

//    public static void parseDOM(String path) {
//        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder db = dbf.newDocumentBuilder();
//            System.out.println("path:" + DBLP_PATH);
//            Document doc = db.parse(DBLP_PATH);
//
//            NodeList articleList = doc.getElementsByTagName("article");
//            System.out.println("共有" + articleList.getLength() + "个dog节点");
//            for (int i = 0; i < 20000; i++) {
//                Node article = articleList.item(i);
//                Element elem = (Element) article;
////                System.out.println("id:" + elem.getAttribute("id"));
////                for (Node node = article.getFirstChild(); node != null; node = node.getNextSibling())
////                {
////                    if (node.getNodeType() == Node.ELEMENT_NODE)
////                    {
////                        String name = node.getNodeName();
////                        String value = node.getFirstChild().getNodeValue();
////                        System.out.print(name + ":" + value + "\t");
////                    }
////                }
//                System.out.println();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

}
