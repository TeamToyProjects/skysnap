package com.toyproject.skysnap.airline.mapper;

import com.toyproject.skysnap.airline.dto.AirlineDto;
import com.toyproject.skysnap.airline.entitiy.Airline;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AirlineMapper {
    AirlineDto airlineToAirlineDto(Airline airline);
}
