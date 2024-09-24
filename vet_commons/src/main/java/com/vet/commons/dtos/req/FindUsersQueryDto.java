package com.vet.commons.dtos.req;

import java.util.List;

import com.vet.commons.dtos.res.UserDto;

public class FindUsersQueryDto extends IFindAllQueryDto<UserDto> {

    FindUsersQueryDto(List<UserDto> data, Integer page, Integer pageSize) {
        super(data, page, pageSize);
    }}
