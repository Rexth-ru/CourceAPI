package com.example.easyauction.controller;
import com.example.easyauction.dto.*;
import com.example.easyauction.en.Status;
import com.example.easyauction.service.BidService;
import com.example.easyauction.service.LotService;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping("lot")
public class Lots {
    private final LotService lotService;
    private final BidService bidService;

    public Lots(LotService lotService, BidService bidService) {
        this.lotService = lotService;
        this.bidService = bidService;
    }

//    Возвращает первого ставившего на этот лот
    @GetMapping("{id}/first")
    public ResponseEntity<?> getFirstBidder(@PathVariable Integer id){
        BidDTO bidDTO = bidService.getFirstBidByIdLot(id);
        if (bidDTO==null){
           return ResponseEntity.status(404).body("Лот не найден");
        }
        return ResponseEntity.ok(bidDTO);
    }
//Возвращает имя ставившего на данный лот наибольшее количество раз
    @GetMapping("{id}/frequent")
    public ResponseEntity<?> getMostFrequentBidder(@PathVariable Integer id){
        if (lotService.findMostFrequentBidder(id)==null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Лот не найден");
        }
        return ResponseEntity.ok(lotService.findMostFrequentBidder(id));
    }
//Получить полную информацию о лоте
    @GetMapping("{id}")
    public ResponseEntity getFullLot(@PathVariable Integer id){
        if (lotService.getFullLotById(id)==null){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Лот не найден");
        }
        return ResponseEntity.ok(lotService.getFullLotById(id));
    }
//Начать торги по лоту. Переводит лот в состояние "начато", которое позволяет делать ставки на лот.
// Если лот уже находится в состоянии "начато", то ничего не делает и возвращает 200
    @SneakyThrows
    @PostMapping("{id}/start")
    public void startBidding(@PathVariable Integer id) {
        lotService.startBidding(id);
    }
//Создает новую ставку по лоту. Если лот в статусе CREATED или STOPPED, то должна вернутся ошибка
    @PostMapping("{id}/bid")
    public ResponseEntity createBid(@PathVariable Integer id, @RequestBody CreatBid creatBid) throws IOException {
        bidService.createBid(id, creatBid);
       return ResponseEntity.status(200).body("Ставка сделана");
    }
//Остановить торги по лоту.Переводит лот в состояние "остановлен", которое запрещает делать ставки на лот.
// Если лот уже находится в состоянии "остановлен", то ничего не делает и возвращает 200
    @SneakyThrows
    @PostMapping("{id}/stop")
    public void stopBidding(@PathVariable Integer id){
        lotService.stopBidding(id);
    }
//Создает новый лот.Метод создания нового лота,
// если есть ошибки в полях объекта лота - то нужно вернуть статус с ошибкой
    @PostMapping
    public ResponseEntity createLot(@RequestBody CreatLot creatLot) {
        return ResponseEntity.ok(lotService.createLot(creatLot));
    }
//Получить все лоты, основываясь на фильтре статуса и номере страницы.
//Возвращает все записи о лотах без информации о текущей цене и победителе постранично.
// Если страница не указана, то возвращается первая страница. Номера страниц начинаются с 0.
// Лимит на количество лотов на странице - 10 штук.

    @GetMapping()
    public ResponseEntity findLots(@RequestParam Status status, @RequestParam Integer page){

        return ResponseEntity.ok(lotService.findAllLotsByStatus(status,page));
    }
//Экспортировать все лоты в формате id,title,status,lastBidder,currentPrice в одном файле CSV
    @GetMapping("export")
    public void getCSVFile(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.addHeader("Content-Disposition", "attachment; filename=\"lot.csv\"");
        lotService.writeLotsToCsv(lotService.findAll(), response.getWriter());
    }
}
