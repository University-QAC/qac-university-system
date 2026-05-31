package com.effortcure.qac.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.effortcure.qac.model.RefreshToken;

import jakarta.transaction.Transactional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID> {
    @Query(value = "SELECT is_revoked FROM refresh_token WHERE token = :token", nativeQuery = true)
    Boolean isRevoked(@Param("token") String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE refresh_token SET is_revoked = true WHERE token = :token", nativeQuery = true)
    int revokeRefreshToken(@Param("token") String token);

    @Modifying
    @Transactional
    @Query(value = "UPDATE refresh_token SET is_revoked = true WHERE account_uuid = :accountUuid", nativeQuery = true)
    int revokeAllRefreshTokens(@Param("accountUuid") UUID accountUuid);
}
