/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kfu.project.service;

import org.springframework.stereotype.Service;

@Service
public interface Crypter {
    String crypt(String string);
}
