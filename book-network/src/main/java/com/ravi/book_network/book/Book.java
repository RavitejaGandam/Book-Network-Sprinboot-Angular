package com.ravi.book_network.book;

import com.ravi.book_network.common.BaseEntity;
import com.ravi.book_network.entity.User;
import com.ravi.book_network.feedback.Feedback;
import com.ravi.book_network.history.BookTransactionHistory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class Book  extends BaseEntity {

    private String title;
    private String authorName;
    private String isbn;
    private String synopsis;
    private String bookCover;
    private boolean archived;
    private boolean shareable;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;
    @OneToMany(mappedBy = "book")
    private List<Feedback> feedbacks;

    @OneToMany(mappedBy = "book")
    private List<BookTransactionHistory> histories;

    @Transient
    public double getRate(){
        if(feedbacks == null || feedbacks.isEmpty()){
            return 0.0;
        }
        var rate =  this.feedbacks.stream()
                .mapToDouble(Feedback::getNote)
                .average()
                .orElse(0.0);
        return Math.round(rate * 10.0)/10.0;
    }
}