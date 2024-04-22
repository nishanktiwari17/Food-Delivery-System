package org.fdschinecenter.request;

import org.fdschinecenter.dto.UserDto;
/**
 * Class to manage OrderRequest.
 */
public record OrderRequest (int id,
                            String name,
                            String description,
                            Double price,
                            String restaurantId,
                            UserDto userDto){
}
