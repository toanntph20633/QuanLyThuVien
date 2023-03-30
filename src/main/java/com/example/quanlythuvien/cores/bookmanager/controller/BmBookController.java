package com.example.quanlythuvien.cores.bookmanager.controller;

import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookCreateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.request.BmBookUpdateReq;
import com.example.quanlythuvien.cores.bookmanager.entity.request.BmListBookReq;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmBookRes;
import com.example.quanlythuvien.cores.bookmanager.entity.response.BmListBookRes;
import com.example.quanlythuvien.cores.bookmanager.service.BmBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/admin/book-manager/api/v1")
public class BmBookController {
    @Autowired
    private BmBookService bmBookService;

    @PostMapping("list")
    public ResponseEntity<List<BmListBookRes>> getList(@RequestBody BmListBookReq req) {
        return ResponseEntity.ok(bmBookService.getList(req));
    }

    @PostMapping("create")
    public ResponseEntity<String> create(@RequestBody BmBookCreateReq req) {
        bmBookService.save(req);
        return ResponseEntity.ok("Save done!");
    }

    @PutMapping("update")
    public ResponseEntity<String> update(@RequestBody BmBookUpdateReq req) {
        bmBookService.update(req);
        return ResponseEntity.ok("Update done!");
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        bmBookService.delete(id);
        return ResponseEntity.ok("Delete done!");
    }

    @GetMapping("detail/{id}")
    public ResponseEntity<BmBookRes> getOne(@PathVariable UUID id) {
        return ResponseEntity.ok(bmBookService.getOne(id));
    }
}
