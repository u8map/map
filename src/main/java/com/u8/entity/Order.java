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
    @Column
    private String order_no;
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
    @Column
    private Integer is_pay;
    @Column
    private Integer pay_type;
    @Column
    private Long pay_time;
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
    @Column
    private Integer apply_delete;
    @Column
    private Long addtime;
    @Column
    private Integer is_delete;
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
    @Column
    private Integer is_cancel;
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
    @Column
    private Integer is_recycle;
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
