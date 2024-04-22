package org.fdschinecenter.dto;

import org.fdschinecenter.entity.Role;

import java.io.Serializable;
import java.util.UUID;
/**
 * Class to manage UserDto.
 */
public record UserDto(UUID id,
                      String name,
                      String email,
                      String phone,
                      String address,
                      boolean enable,
                      Role role) implements Serializable {
}
