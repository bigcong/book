package cn.zhsit.generator.models.vo;

import cn.zhsit.generator.models.po.MessageGeneral;

import java.util.Date;

/**
 * Created by Darren on 2017/8/12.
 */
public class MessageGeneralResp {
    private MessageGeneral m;
    /**
     *
     */
    private String id;

    /**
     * 接收方:人员ID等
     */
    private String receiverId;
    /**
     * 接收方昵称
     */
    private String receiverNick;


    /**
     * 发送方:若是系统通知，直接写入“系统通知”四个字
     */
    private String senderId;
    /**
     * 发送者昵称
     */
    private String senderNick;
    /**
     *  标题
     */
    private String title;
    /**
     * 消息内容
     */
    private String content;

    /**
     * 状态:1，未读；2，已读；
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 图片路径
     */
    private String path;

    public MessageGeneral getM() {
        return m;
    }

    public MessageGeneralResp setM(MessageGeneral m) {
        this.m = m;
        return this;
    }

    public String getId() {
        return id;
    }

    public MessageGeneralResp setId(String id) {
        this.id = id;
        return this;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public MessageGeneralResp setReceiverId(String receiverId) {
        this.receiverId = receiverId;
        return this;
    }

    public String getSenderId() {
        return senderId;
    }

    public MessageGeneralResp setSenderId(String senderId) {
        this.senderId = senderId;
        return this;
    }

    public String getContent() {
        return content;
    }

    public MessageGeneralResp setContent(String content) {
        this.content = content;
        return this;
    }

    public Byte getStatus() {
        return status;
    }

    public MessageGeneralResp setStatus(Byte status) {
        this.status = status;
        return this;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public MessageGeneralResp setCreateTime(Date createTime) {
        this.createTime = createTime;
        return this;
    }

    public String getPath() {
        return path;
    }

    public MessageGeneralResp setPath(String path) {
        this.path = path;
        return this;
    }

    public String getReceiverNick() {
        return receiverNick;
    }

    public MessageGeneralResp setReceiverNick(String receiverNick) {
        this.receiverNick = receiverNick;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MessageGeneralResp setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSenderNick() {
        return senderNick;
    }

    public MessageGeneralResp setSenderNick(String senderNick) {
        this.senderNick = senderNick;
        return this;
    }
}
