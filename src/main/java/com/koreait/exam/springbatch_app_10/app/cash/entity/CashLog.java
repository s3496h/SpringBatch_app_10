package com.koreait.exam.springbatch_app_10.app.cash.entity;

import com.koreait.exam.springbatch_app_10.app.base.entity.BaseEntity;
import com.koreait.exam.springbatch_app_10.app.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import static javax.persistence.FetchType.LAZY;
@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString(callSuper = true)
public class CashLog extends BaseEntity {
    @ManyToOne(fetch = LAZY)
    private Member member;
    private long price;
    private String eventType;
}