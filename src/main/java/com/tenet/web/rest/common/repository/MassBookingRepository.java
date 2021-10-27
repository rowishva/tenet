package com.tenet.web.rest.common.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.tenet.web.rest.common.entity.MassBooking;

@Repository
public interface MassBookingRepository extends JpaRepository<MassBooking, Long>, JpaSpecificationExecutor<MassBooking> {

	@Query(value = "SELECT fnt_mass_booking(?1)", nativeQuery = true)
	String fnt_mass_booking(Long id);

	@Query(value = "SELECT nextval('trn_mass_booking_no')", nativeQuery = true)
	Long getNextMassBookingNo();

	@Query(value = "SELECT * from trn_mass_booking mb where mb.mass_time_id=?1 and mb.prefix =?2 and mb.tag =?3 and mb.booked is false ORDER BY mb.seating_no asc LIMIT ?4 ", nativeQuery = true)
	List<MassBooking> findByMassBookingList(long massTimeId, String prefix, String tag, int limit);

	@Query(value = "SELECT * from trn_mass_booking mb where mb.mass_time_id=?1 and mb.tag =?2 and mb.booked is false ORDER BY mb.seating_no asc LIMIT 1 ", nativeQuery = true)
	MassBooking findByMassTimeIdandTagLimit1(long massTimeId, String tag);

	@Query(value = "SELECT * from trn_mass_booking mb where mb.mass_time_id=?1 and mb.mass_booking_no = 'CORETEAM' and mb.booked is true ORDER BY mb.seating_no", nativeQuery = true)
	List<MassBooking> findByCoreTeamList(long massTimeId);

	@Query(value = "SELECT * from trn_mass_booking mb where mb.profile_id=?1 and mb.booked is true ORDER BY mb.mass_time_id DESC", nativeQuery = true)
	List<MassBooking> findByProfileId(long profileId);

	@Query(value = "SELECT * from trn_mass_booking mb where mb.mass_time_id=?1 and mb.mass_booking_no = 'WALKIN' and mb.booked is true ORDER BY mb.seating_no", nativeQuery = true)
	List<MassBooking> findByWalkInList(long massTimeId);

}
