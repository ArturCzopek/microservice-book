package pl.simplecoding.library.reservations

import org.springframework.stereotype.Service

@Service
class ReservationService(private val reservationRepository: ReservationRepository) {

    fun getAllReservations() = reservationRepository.findAll()

    fun getUserReservations(userId: Long) = reservationRepository.findAllByUser_Id(userId)

    fun getBookReservations(bookId: Long) = reservationRepository.findAllByBookId(bookId)
}
