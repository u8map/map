package com.u8.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;


/**
 * 订单实体类
 */
@Data
@Entity
@Table(name = "hjmall_order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @Column
    private Long store_id;
    @Column
    private Long user_id;
    @Column(name="order_no")
    private String orderNo;
    @Column
    private Double total_price;
    @Column
    private Double pay_price;
    @Column
    private Double express_price;
    @Column
    private String name;
    @Column
    private String mobile;
    @Column
    private String address;
    @Column
    private String remark;
    @Column(name="is_pay")
    private Integer isPay;
    @Column
    private Integer pay_type;
    @Column(name="pay_time")
    private Long payTime;
    @Column(name="is_send")
    private Integer isSend;
    @Column
    private Long send_time;
    @Column
    private String express;
    @Column
    private String express_no;
    @Column
    private Integer is_confirm;
    @Column
    private Long confirm_time;
    @Column
    private Integer is_comment;
    @Column(name = "apply_delete")
    private Integer applyDelete;
    @Column
    private Long addtime;
    @Column(name = "is_delete")
    private Integer isDelete;
    @Column
    private Integer is_price;
    @Column
    private Long parent_id;
    @Column
    private Double first_price;
    @Column
    private Double second_price;
    @Column
    private Double third_price;
    @Column
    private Double coupon_sub_price;
    @Column
    private String content;
    @Column
    private Long is_offline;
    @Column
    private Long clerk_id;
    @Column
    private String address_data;
    @Column(name = "is_cancel")
    private Integer isCancel;
    @Column
    private String offline_qrcode;
    @Column
    private Double before_update_price;
    @Column
    private Long shop_id;
    @Column
    private Double discount;
    @Column
    private Long user_coupon_id;
    @Column
    private String integral;
    @Column
    private Integer give_integral;
    @Column
    private Long parent_id_1;
    @Column
    private Long parent_id_2;
    @Column
    private Long is_sale;
    @Column
    private String words;
    @Column
    private String version;
    @Column
    private Double express_price_1;
    @Column(name = "is_recycle")
    private Integer isRecycle;
    @Column
    private Double rebate;
    @Column
    private Double before_update_express;
    @Column
    private String seller_comments;
    @Column
    private Long mch_id;
    @Column
    private Long order_union_id;
    @Column
    private Integer is_transfer;
    @Column
    private Long type;
    @Column
    private Double share_price;
    @Column
    private Integer is_show;
    @Column
    private Double currency;
}
