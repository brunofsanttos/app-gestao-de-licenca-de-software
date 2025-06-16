package com.br.gestaodelicencadesoftware.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProofDTO {
    private Long proofId;
    private String fileName;
    private String fileHash;
    private String fileExtension;
    private String fileContentType;
    private String fileUrl;
    private Boolean isDeleted;
}
