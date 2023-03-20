package ru.sbercources.library.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "publishing")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SequenceGenerator(name = "default_generator", sequenceName = "publishing_seq", allocationSize = 1)
public class Publish extends GenericModel {
    @Column(name = "rent_date", nullable = false)
    private LocalDateTime rentDate;

    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;

    @Column(name = "returned", nullable = false)
    private boolean returned;

    @Column(name = "rent_period", nullable = false)
    private Integer rentPeriod;

    @Column(name = "amount")
    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(name = "FK_PUBLISHING_USER"))
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", foreignKey = @ForeignKey(name = "FK_PUBLISHING_BOOK"))
    private Book book;

    @Builder
    public Publish(Long id, String createdBy, LocalDateTime createdWhen, LocalDateTime updatedWhen, String updatedBy, boolean isDeleted,
                   LocalDateTime deletedWhen,
                   String deletedBy, LocalDateTime rentDate, LocalDateTime returnDate, boolean returned, Integer rentPeriod, Integer amount, User user, Book book) {
        super(id, createdBy, createdWhen, updatedWhen, updatedBy, isDeleted, deletedWhen, deletedBy);
        this.rentDate = rentDate;
        this.returnDate = returnDate;
        this.returned = returned;
        this.rentPeriod = rentPeriod;
        this.amount = amount;
        this.user = user;
        this.book = book;
    }
}