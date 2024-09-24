package com.vet.commons.dtos.req;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class IFindAllQueryDto<T> {
    private List<T> data;
    private Integer page;
    private Integer pageSize;
}
