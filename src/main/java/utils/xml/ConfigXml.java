package utils.xml;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：SINOPEC-CTS<br/>
 * *********************************<br/>
 * <p>类名称：ConfigXml</p>
 * *********************************<br/>
 * <p>类描述：</p>
 * 创建人：zhouzhihui<br/>
 * 创建时间：2017/3/10 17:33<br/>
 * 修改人：周志辉<br/>
 * 修改时间：2017/3/10 17:33<br/>
 * 修改备注：<br/>
 *
 * @version 1.0<br/>
 */
public abstract class ConfigXml<T> {

    /**
     * 根节点
     */
    protected Element root;

    /**
     * 文档对象
     */
    protected Document doc;

    /**
     * 配置对象
     */
    protected T config;

    /**
     * xml文件生成路径
     */
    protected String filePath;

    /**
     * 当前操作节点对象
     */
    protected Element currentOperatedNode;

    /**
     * 从根节点到当前操作节点对象的节点列表
     */
    protected List<Element> currentOperatedList = new ArrayList();


    /**
     * <p>方法描述: 构造方法</p>
     *
     * <p>方法备注: </p>
     * @param   rootName    根节占名
     * @param   config      配置对象
     * @param   filePath    xml文件生成路径
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:43</p>
     */
    public ConfigXml(String rootName, T config, String filePath) {
        this.config = config;
        this.filePath = filePath;
        this.root = new Element("project");
        this.doc = new Document(root);
        this.currentOperatedNode = root;
    }

    /**
     * <p>方法描述: 根据配置对象添加节点</p>
     *
     * <p>方法备注: </p>
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:44</p>
     */
    protected abstract void addNodesWithConfig();


    /**
     * <p>方法描述: 添加无内容子节点</p>
     *
     * <p>方法备注: </p>
     * @param   nodeNames   节点名
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:05</p>
     */
    protected ConfigXml addEmptyNodes(String... nodeNames) {
        Element newNode;
        for (String nodeName : nodeNames) {
            newNode = new Element(nodeName);
            currentOperatedNode.addContent(newNode);
        }
        return this;
    }


    /**
     * <p>方法描述: 添加子结点</p>
     *
     * <p>方法备注: </p>
     * @param nodeName  节点名
     * @param nodeContent   节点内容
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:09</p>
     */
    protected ConfigXml addNode(String nodeName, String nodeContent) {
        Element newNode = new Element(nodeName).setText(nodeContent);
        currentOperatedNode.addContent(newNode);
        return this;
    }

    /**
     * <p>方法描述: 添加子结点</p>
     *
     * <p>方法备注: </p>
     * @param nodeName  节点名
     * @param nodeContent  节点内容
     * @param attributeName   属性名
     * @param attributeValue   属性值
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:09</p>
     */
    protected ConfigXml addNode(String nodeName, String nodeContent, String attributeName, String attributeValue) {
        Element newNode = new Element(nodeName).setText(nodeContent).setAttribute(attributeName, attributeValue);
        currentOperatedNode.addContent(newNode);
        return this;
    }


    /**
     * <p>方法描述: 批量添加子结点</p>
     *
     * <p>方法备注: </p>
     * @param   nodeNames   节点名列表
     * @param   nodeContents    节点内容列表
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:11</p>
     */
    protected ConfigXml addNodes(List<String> nodeNames, List<String> nodeContents) {
        for (int i = 0; i < Math.min(nodeNames.size(), nodeContents.size()); i++) {
            addNode(nodeNames.get(i), nodeContents.get(i));
        }
        return this;
    }


    /**
     * <p>方法描述: 添加一个复合节点，并置添加的节点为当前操作结点</p>
     *
     * <p>方法备注: </p>
     * @param   nodeName    节点名
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:13</p>
     */
    protected ConfigXml addMultiNode(String nodeName) {
        Element newNode = new Element(nodeName);
        currentOperatedNode.addContent(newNode);
        currentOperatedList.add(currentOperatedNode);
        currentOperatedNode = newNode;
        return this;
    }


    /**
     * <p>方法描述: 添加一个复合节点，置添加的节点为当前操作结点，同时添加一个属性</p>
     *
     * <p>方法备注: </p>
     * @param   nodeName        节点名
     * @param   attributeName   属性名
     * @param   attributeValue  属性值
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:15</p>
     */
    protected ConfigXml addMultiNode(String nodeName, String attributeName, String attributeValue) {
        Element newNode = new Element(nodeName).setAttribute(attributeName, attributeValue);
        currentOperatedNode.addContent(newNode);
        currentOperatedList.add(currentOperatedNode);
        currentOperatedNode = newNode;
        return this;
    }


    /**
     * <p>方法描述: 给当前节点添加属性值</p>
     *
     * <p>方法备注: </p>
     * @param   attributeName   属性名
     * @param   attributeValue  属性值
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 10:03</p>
     */
    protected ConfigXml setAttribute(String attributeName, String attributeValue) {
        currentOperatedNode.setAttribute(attributeName, attributeValue);
        return this;
    }

    /**
     * <p>方法描述: 重置当前操作节点为根节点</p>
     *
     * <p>方法备注: </p>
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:28</p>
     */
    protected ConfigXml reset() {
        currentOperatedNode = root;
        currentOperatedList.clear();
        return this;
    }


    /**
     * <p>方法描述: 按指定层数回溯操作节点列表，并设置为当前操作节点</p>
     *
     * <p>方法备注: </p>
     * @param depthNumber   回溯层数
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:28</p>
     */
    protected ConfigXml traceBack(int depthNumber) {
        if(depthNumber > currentOperatedList.size()) {
            return null;
        }
        currentOperatedNode = currentOperatedList.get(currentOperatedList.size() - depthNumber);
        for (int i = currentOperatedList.size() - depthNumber; i < currentOperatedList.size(); i++) {
            currentOperatedList.remove(i);
        }
        return this;
    }


    /**
     * <p>方法描述: 按指定节点名回溯操作节点列表，并设置为当前操作节点</p>
     *
     * <p>方法备注: </p>
     * @param nodeName   回溯层数
     * @return  当前对象
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:28</p>
     */
    protected ConfigXml traceBack(String nodeName) {
        boolean flag = false;
        for (int i = 0; i < currentOperatedList.size(); i++) {
            if(flag) {
                currentOperatedList.remove(i);
            }
            else if(currentOperatedList.get(i).getName().equals(nodeName)) {
                if(i == 0) {
                    currentOperatedList.clear();
                }else {
                    currentOperatedNode = currentOperatedList.get(i);
                    currentOperatedList.remove(i);
                    flag = true;
                }

            }
        }
        return this;
    }


    /**
     * <p>方法描述: 从根节点查找指定名称的节点，并置为当前操作节点</p>
     *
     * <p>方法备注: 未找到则currentOperateNode值不变</p>
     * @param nodeName   节点名
     * @return  是否成功
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:28</p>
     */
    protected boolean setNode(String nodeName) {
        Element element = root.getChild(nodeName);
        if(element != null) {
            currentOperatedNode = element;
            return true;
        }
        return false;
    }


    /**
     * <p>方法描述: 按指定路径生成xml文件</p>
     *
     * <p>方法备注: </p>
     * @return  是否成功
     * <p>创建人：周志辉</p>
     * <p>创建时间：2017/3/13 9:25</p>
     */
    public boolean createXmlFile() {
        addNodesWithConfig();
        Format format = Format.getPrettyFormat();
        XMLOutputter XMLOut = new XMLOutputter(format);
        try(FileOutputStream fos = new FileOutputStream(filePath)) {
            XMLOut.output(doc, fos);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    //测试用，获取当前操作节点名
    @Deprecated
    protected String getCurrentNodeName() {
        if(currentOperatedNode != null) {
            return currentOperatedNode.getName();
        }
        return "";
    }
}
