package academy.wesley601.springboot2essentials.service;

import academy.wesley601.springboot2essentials.domain.Anime;
import academy.wesley601.springboot2essentials.exception.BadRequestException;
import academy.wesley601.springboot2essentials.mapper.AnimeMapper;
import academy.wesley601.springboot2essentials.repolitory.AnimeRepository;
import academy.wesley601.springboot2essentials.requests.AnimePostRequestBody;
import academy.wesley601.springboot2essentials.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class AnimeService {
    private final AnimeRepository animeRepository;

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findById(long id) {
        return animeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Anime not found"));
    }

    @Transactional
    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findById(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        findById(animePutRequestBody.getId());
        animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePutRequestBody));
    }
}
