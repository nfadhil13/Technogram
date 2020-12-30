package com.fdev.technogram.datasource


/*
    This is an interface than can be used to map from domain model to dto or dao and vice versa
 */
interface DomainMapper<T , Domain>{

    fun mapFromDomain(domain : Domain) : T

    fun mapToDomain(t : T) : Domain


}