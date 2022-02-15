package com.alzal.nadeulseoulbackend.domain.curations.repository;

import com.alzal.nadeulseoulbackend.domain.curations.entity.Curation;
import com.alzal.nadeulseoulbackend.domain.curations.util.QueryDslUtil;
import com.alzal.nadeulseoulbackend.domain.tag.dto.CodeRequestDto;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

import static com.alzal.nadeulseoulbackend.domain.curations.entity.QCuration.curation;
import static com.alzal.nadeulseoulbackend.domain.curations.entity.QLocalCuration.localCuration;
import static com.alzal.nadeulseoulbackend.domain.curations.entity.QThemeCuration.themeCuration;

@RequiredArgsConstructor
public class CurationTagRepositoryImpl implements CurationTagRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    private List<OrderSpecifier> getAllOrderSpecifiers(Pageable pageable) {
        List<OrderSpecifier> ORDERS = new ArrayList<>();
        if (pageable.getSort() != null) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                switch (order.getProperty()) {
                    case "views":
                        OrderSpecifier<?> orderViews = QueryDslUtil.getSortedColumn(direction, curation.views, "views");
                        ORDERS.add(orderViews);
                        break;
                    case "good":
                        OrderSpecifier<?> orderGood = QueryDslUtil.getSortedColumn(direction, curation.good, "good");
                        ORDERS.add(orderGood);
                        break;
                    case "date":
                        OrderSpecifier<?> orderDate = QueryDslUtil.getSortedColumn(direction, curation.date, "date");
                        ORDERS.add(orderDate);
                        break;
                    default:
                        break;
                }
            }
        }

        return ORDERS;
    }

    @Override
    public Page<Curation> searchByTag(CodeRequestDto codeRequestDto, Pageable pageable) {
        List<Long> localList = codeRequestDto.getLocal();
        List<Long> themeList = codeRequestDto.getTheme();
        List<OrderSpecifier> ORDERS = getAllOrderSpecifiers(pageable);

        QueryResults<Curation> results = jpaQueryFactory
                .select(curation)
                .from(curation)
                .join(localCuration).on(curation.curationSeq.eq(localCuration.curation.curationSeq))
                .join(themeCuration).on(curation.curationSeq.eq(localCuration.curation.curationSeq))
                .where(localCuration.code.codeSeq.in(localList), themeCuration.code.codeSeq.in(themeList))
                .groupBy(curation.curationSeq)
                .orderBy(ORDERS.stream().toArray(OrderSpecifier[]::new))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        return new PageImpl<>(results.getResults(), pageable, results.getTotal());
    }
}
