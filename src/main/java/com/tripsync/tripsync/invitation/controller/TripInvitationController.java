package com.tripsync.tripsync.invitation.controller;


import com.tripsync.tripsync.invitation.dto.InvitationResponse;
import com.tripsync.tripsync.invitation.dto.InviteMemberRequest;
import com.tripsync.tripsync.invitation.service.TripInvitationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/trips/{tripId}/invitations")
public class TripInvitationController {
    private final TripInvitationService tripInvitationService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public InvitationResponse inviteMember(@PathVariable Long tripId,
                                           @Valid @RequestBody InviteMemberRequest request, Authentication authentication){
        return tripInvitationService.inviteMember(tripId,request,getAuthenticationEmail(authentication));

    }

    private String getAuthenticationEmail(Authentication authentication){
        if (authentication==null||!authentication.isAuthenticated()){
            throw new org.springframework.web.server.ResponseStatusException(HttpStatus.UNAUTHORIZED,"Authentication is required");
        }
        return authentication.getName();
    }

    @PostMapping("/accept")
    public InvitationResponse acceptInvitation(@PathVariable Long tripId, @RequestParam String token, Authentication authentication){
        InvitationResponse response = tripInvitationService.acceptInvitation(token,getAuthenticationEmail(authentication));
        if (!response.tripId().equals(tripId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invitation does not belongs to this trip");
        }
        return response;
    }

    @PostMapping("/reject")
    public InvitationResponse rejectInvitation(@PathVariable Long tripId,@RequestParam String token,Authentication authentication){
        InvitationResponse response = tripInvitationService.rejectInvitation(token,getAuthenticationEmail(authentication));
        if (!response.tripId().equals(tripId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invitation does not belongs to this trip");
        }
        return response;
    }
}
