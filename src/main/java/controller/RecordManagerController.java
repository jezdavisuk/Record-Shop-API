package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.AlbumService;

@RestController
@RequestMapping("/api/v1/")
public class RecordManagerController {

    @Autowired
    AlbumService albumService;
}
