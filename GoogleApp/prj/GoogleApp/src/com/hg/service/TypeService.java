package com.hg.service;

import java.util.List;

import com.hg.dto.TypeInfo;

public interface TypeService {

    boolean postType(TypeInfo info);

    boolean updateType(TypeInfo info);

    boolean removeType(String typeId);

    List<TypeInfo> getTypes();

    List<TypeInfo> getTypes(boolean containsDefault);

}
