package com.jaewon.myproject2.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "REPLY_HEART")
public class ReplyHeart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "REPLY_HEART_ID")
    private Long replyHeartId;

    @ManyToOne
    @JoinColumn(name = "REPLY_ID")
    private Reply reply;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;
}
