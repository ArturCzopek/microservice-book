package pl.simplecoding.library.reservations

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/reservations")
class ReservationController(private val reservationService: ReservationService) {

    @GetMapping("/")
    fun getAllReservations() = reservationService.getAllReservations()

    @GetMapping("/user/{userId}")
    fun getUserReservations(@PathVariable userId: Long) = reservationService.getUserReservations(userId)

    @GetMapping("/book/{bookId}")
    fun getBookReservations(@PathVariable bookId: Long) = reservationService.getBookReservations(bookId)
}
